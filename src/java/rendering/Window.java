package rendering;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

	protected final long id;
	
	int vao, vbo;
	
	public Window(String title, int width, int height) throws Exception {
		
		this.id = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
	}
	
	public void init() {
		float [] vertices = new float [] {
				0.0f, 0.5f, 0.0f,
				-0.5f, -0.5f, 0.0f,
				0.5f, -0.5f, 0.0f
		};
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	public void makeContextCurrent() {
		GLFW.glfwMakeContextCurrent(id);
	}
	
	public void render() {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GL30.glBindVertexArray(vao);
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		GLFW.glfwSwapBuffers(id);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(id);
	}
	
	public void destroy() {
		GLFW.glfwDestroyWindow(id);
	}
}
