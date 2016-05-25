package rendering;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import rendering.meshes.Mesh;
import rendering.meshes.OctagonalMesh;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

	protected final long id;
	
	private Mesh testMesh;
	
	public Window(String title, int width, int height) throws Exception {
		
		this.id = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
	}
	
	public void init() {
		
		GL11.glClearColor(0.1f, 0.1f, 0.1f, 1f);
		testMesh = new OctagonalMesh(0.25f);

	}
	
	public void makeContextCurrent() {
		GLFW.glfwMakeContextCurrent(id);
	}
	
	public void render() throws Exception {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		testMesh.render();
		GLFW.glfwSwapBuffers(id);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(id);
	}
	
	
	public void destroy() throws Exception {
		
		testMesh.release();
		GLFW.glfwDestroyWindow(id);
	}
}
