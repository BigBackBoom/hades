#version 300 es
precision mediump float;
in vec2 v_texCoord;
layout(location = 0) out vec4 outColor;
uniform sampler2D s_texture;
uniform vec3 rgb;
uniform float alpha;
void main()
{
    // change color order since bitmap is BGR
    float filtering = 1.0 - alpha;
    vec4 texture = texture(s_texture, v_texCoord);
    float r = (texture.b * alpha) + (rgb.r  * filtering);
    float g = (texture.g * alpha) + (rgb.g  * filtering);
    float b = (texture.r * alpha) + (rgb.b  * filtering);
    // change color order since bitmap is BGR
    outColor = vec4(r, g, b, 1.0f);
}