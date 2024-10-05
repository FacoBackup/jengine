out vec4 fragColor;

in float renderId;

float rand(vec2 co) {
    return fract(sin(dot(co, vec2(12.9898, 78.233))) * 43758.5453);
}

vec3 randomColor(float seed) {
    float r = rand(vec2(seed));
    float g = rand(vec2(seed + r));
    return vec3(r, g, rand(vec2(seed + g)));
}
vec3 amplitudeColorGradient(float value) {
    // Clamp the value between 0 and 1
    value = clamp(value/10, 0.0, 1.0);

    vec3 color;

    // Use the value to map across a spectrum of colors
    if (value < 0.2) {
        // From Red to Yellow
        color = mix(vec3(1.0, 0.0, 0.0), vec3(1.0, 1.0, 0.0), value / 0.2);
    } else if (value < 0.4) {
        // From Yellow to Green
        color = mix(vec3(1.0, 1.0, 0.0), vec3(0.0, 1.0, 0.0), (value - 0.2) / 0.2);
    } else if (value < 0.6) {
        // From Green to Cyan
        color = mix(vec3(0.0, 1.0, 0.0), vec3(0.0, 1.0, 1.0), (value - 0.4) / 0.2);
    } else if (value < 0.8) {
        // From Cyan to Blue
        color = mix(vec3(0.0, 1.0, 1.0), vec3(0.0, 0.0, 1.0), (value - 0.6) / 0.2);
    } else {
        // From Blue to Purple
        color = mix(vec3(0.0, 0.0, 1.0), vec3(1.0, 0.0, 1.0), (value - 0.8) / 0.2);
    }

    return color;
}
void main() {

    fragColor = vec4(amplitudeColorGradient(renderId), 1.);
}
