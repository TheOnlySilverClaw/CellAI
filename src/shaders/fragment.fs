# version 330

out vec4 color;
in vec3 genColor;

void main() {
	color = vec4(genColor, 1);
}