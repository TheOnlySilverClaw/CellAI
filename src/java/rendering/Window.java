package rendering;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

	protected final long id;
	
	int idxvbo, vao, vbo;
	float [] vertices;
	int [] indices;
	
	public Window(String title, int width, int height) throws Exception {
		
		this.id = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
	}
	
	public void init() {
		vertices = new float [] {
				-0.5f,	0.5f,	// 0
				-0.5f,	-0.5f,	// 1
				0.5f,	-0.5f,	// 2
				0.5f,	0.5f,	// 3
				0.9f,	0.6f	// 4
		};
		
		indices  = new int [] {
				0, 1, 3,
				3, 1, 2,
				3, 4, 2
		};

		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);

		idxvbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, idxvbo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		
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
		GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		GLFW.glfwSwapBuffers(id);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(id);
	}
	
	
	public void destroy() {
		
		GL15.glDeleteBuffers(idxvbo);
		GL15.glDeleteBuffers(vbo);
		GL30.glDeleteVertexArrays(vao);
		GLFW.glfwDestroyWindow(id);
	}
}
