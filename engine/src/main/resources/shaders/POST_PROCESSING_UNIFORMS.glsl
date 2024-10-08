// TODO - SIMPLE UNIFORMS, NO NEED FOR UBO SINCE THIS WILL ONLY EXECUTE ONCE PER FRAME

uniform LensEffects{

    float textureSizeXDOF;
    float textureSizeYDOF;
    float distortionIntensity;
    float chromaticAberrationIntensity;
    bool distortionEnabled;
    bool chromaticAberrationEnabled;
    bool bloomEnabled;

    float focusDistanceDOF;
    float apertureDOF;
    float focalLengthDOF;
    float samplesDOF;

    bool vignetteEnabled;
    float vignetteStrength;
    float gamma;
    float exposure;

};