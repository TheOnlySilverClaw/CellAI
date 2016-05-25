# version 330

layout(location=0) in vec2 pos;
out vec3 genColor;

void main() {
	gl_Position = vec4(pos, 0.0, 1.0);
	genColor = vec3(pos.x + 0.5, pos.y + 0.5, pos.x + 0.75);
}