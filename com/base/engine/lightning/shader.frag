uniform vec2 lightLocation;
uniform vec3 lightColor;
uniform float lightAtenuation;
uniform float screenHeight;

void main() {
	float distance = length(lightLocation - gl_FragCoord.xy);
	float attenuation = lightAtenuation / distance;
	vec4 color = vec4(attenuation, attenuation, attenuation, pow(attenuation, 3)) * vec4(lightColor, 1);

	gl_FragColor = color;
}