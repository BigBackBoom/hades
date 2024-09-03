#include <EGL/egl.h>
#include "gles3jni.h"
#include "simple_renderer_es3.h"

//
// Created by kikuchi.kodai on 2024/08/25.
//
static const char SIMPLE_VERTEX_SHADER[] =
  "#version 300 es                            \n"
  "layout(location = 0) in vec4 a_position;   \n"
  "layout(location = 1) in vec2 a_texCoord;   \n"
  "out vec2 v_texCoord;                       \n"
  "void main()                                \n"
  "{                                          \n"
  "   gl_Position = a_position;               \n"
  "   v_texCoord = a_texCoord;                \n"
  "}                                          \n";

static const char SIMPLE_FRAGMENT_SHADER[] =
  "#version 300 es                                            \n"
  "precision mediump float;                                   \n"
  "in vec2 v_texCoord;                                        \n"
  "layout(location = 0) out vec4 outColor;                    \n"
  "uniform sampler2D s_texture;                               \n"
  "uniform vec3 rgb;                                          \n"
  "uniform float alpha;                                       \n"
  "void main()                                                \n"
  "{                                                          \n"
  "  // change color order since bitmap is BGR                \n"
  "  float filtering = 1.0 - alpha;                           \n"
  "  vec4 texture = texture( s_texture, v_texCoord );         \n"
  "  float r = (texture.b * alpha) + (rgb.r  * filtering);   \n"
  "  float g = (texture.g * alpha) + (rgb.g  * filtering);   \n"
  "  float b = (texture.r * alpha) + (rgb.b  * filtering);   \n"
  "  // change color order since bitmap is BGR                \n"
  "  outColor = vec4(r, g, b, 1.0f);                          \n"
  "}                                                          \n";

SimpleRendererES3* createSimpleRendererES3(int x, int y, unsigned char *data) {
  SimpleRendererES3* renderer = new SimpleRendererES3;
  if (!renderer->init(x, y, data)) {
    delete renderer;
    return NULL;
  }
  return renderer;
}

SimpleRendererES3::SimpleRendererES3(): mEglContext(eglGetCurrentContext()), mProgram(0) {
}

bool SimpleRendererES3::init(int x, int y, unsigned char *data) {
  mProgram = createProgram(SIMPLE_VERTEX_SHADER, SIMPLE_FRAGMENT_SHADER);
  if (!mProgram) return false;

  setText(x,  y, data);
  return true;
}

void SimpleRendererES3::draw(int x, int y, const int *rgba) const {
  GLfloat vVertices[] = { -1.0f,  1.0f, 0.0f,  // Position 0
                          0.0f, 1.0f,        // TexCoord 0
                          -1.0f, -1.0f, 0.0f,  // Position 1
                          0.0f,  0.0f,        // TexCoord 1
                          1.0f, -1.0f, 0.0f,  // Position 2
                          1.0f, 0.0f,        // TexCoord 2
                          1.0f,  1.0f, 0.0f,  // Position 3
                          1.0f, 1.0f        // TexCoord 3
  };

  glClear ( GL_COLOR_BUFFER_BIT );

  glUseProgram ( mProgram );

  // Load the vertex position
  glVertexAttribPointer ( 0, 3, GL_FLOAT,
                          GL_FALSE, 5 * sizeof ( GLfloat ), vVertices );
  // Load the texture coordinate
  glVertexAttribPointer ( 1, 2, GL_FLOAT,
                          GL_FALSE, 5 * sizeof ( GLfloat ), &vVertices[3] );

  glEnableVertexAttribArray ( 0 );
  glEnableVertexAttribArray ( 1 );

  // Bind the texture
  glActiveTexture ( GL_TEXTURE0 );
  glBindTexture ( GL_TEXTURE_2D, texture );

  // Set the sampler texture unit to 0
  glUniform1i ( glGetUniformLocation ( mProgram, "s_texture" ), 0 );

  float a = rgba[3] * 0.01f;
  float test[] = {
    rgba[0]/255.0f,
    rgba[1]/255.0f,
    rgba[2]/255.0f
  };
  glUniform3f( glGetUniformLocation ( mProgram, "rgb" ), test[0], test[1], test[2]);

  glUniform1f( glGetUniformLocation ( mProgram, "alpha" ), a );

  glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
}


void SimpleRendererES3::setText(int x, int y, unsigned char *data) {
  // Use tightly packed data
  glPixelStorei ( GL_UNPACK_ALIGNMENT, 1 );

  // Generate a texture object
  glGenTextures ( 1, &texture );

  // Bind the texture object
  glBindTexture ( GL_TEXTURE_2D, texture );

  // Load the texture
  glTexImage2D ( GL_TEXTURE_2D, 0, GL_RGB, x, y, 0, GL_RGB, GL_UNSIGNED_BYTE, data );

  // Set the filtering mode
  glTexParameteri ( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );
  glTexParameteri ( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
}

SimpleRendererES3::~SimpleRendererES3() {
  if (eglGetCurrentContext() != mEglContext) return;


  glDeleteTextures(1, &texture);
  glDeleteProgram(mProgram);
}

