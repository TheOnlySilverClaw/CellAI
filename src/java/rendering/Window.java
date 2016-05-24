package rendering;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import rendering.meshes.IndexMesh;
import rendering.meshes.Mesh;
import rendering.meshes.QuadMesh;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

	protected final long id;
	
	private Mesh testMesh;
	
	public Window(String title, int width, int height) throws Exception {
		
		this.id = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
	}
	
	public void init() {
		
		testMesh = QuadMesh.screenCentered(0.25f);

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
