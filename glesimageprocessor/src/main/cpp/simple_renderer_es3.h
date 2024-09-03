//
// Created by kikuchi.kodai on 2024/09/01.
//

#ifndef HADES_SIMPLE_RENDERER_ES3_H
#define HADES_SIMPLE_RENDERER_ES3_H

#include <EGL/egl.h>
#include <GLES3/gl32.h>

class SimpleRendererES3 {
  public:
    SimpleRendererES3();
    virtual ~SimpleRendererES3();
    GLuint texture;
    bool init(int x, int y, unsigned char *data);
    void draw(int x, int y, const int *rgba) const;

  private:
    const EGLContext mEglContext;
    GLuint mProgram;
    void setText(int x, int y, unsigned char *data);
};

extern SimpleRendererES3* createSimpleRendererES3(int x, int y, unsigned char *data);

#endif //HADES_SIMPLE_RENDERER_ES3_H
