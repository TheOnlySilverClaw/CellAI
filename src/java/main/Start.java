package main;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rendering.ShaderProgram;
import rendering.Window;

import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.*;

public class Start {

	public static void main(String[] args) throws Exception {
		
		Logger log = LoggerFactory.getLogger(Start.class);
		log.info("Starting at '{}'", Instant.now());
		
		
		if(glfwInit()) {
			GLFWErrorCallback error;
			GLFW.glfwSetErrorCallback(error = GLFWErrorCallback.createPrint());

			try {
				
				Path vertexPath = Paths.get("src", "shaders", "vertex.vs");
				Path fragmentPath = Paths.get("src", "shaders", "fragment.fs");

				// nothing works without this...
				GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
				GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
				GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
				GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);				
				
				Window window = new Window("CellAI", 500, 500);
				window.makeContextCurrent();

				GL.createCapabilities();

				window.init();
				ShaderProgram program = new ShaderProgram(
						new String(Files.readAllBytes(vertexPath)),
						new String(Files.readAllBytes(fragmentPath))
						);
				program.link();
				
				while(!window.shouldClose()) {
					
					program.bind();
					window.render();
					program.unbind();
					
					Thread.sleep(50);
					GLFW.glfwPollEvents();
				}				
				program.destroy();
				window.destroy();

			} catch(Exception e) {
				log.error("Error:", e);
			} finally {
				glfwTerminate();
				error.free();
			}
			log.info("GLFW Termianted succesfully.");
		}
		log.info("Shutting down at '{}'", Instant.now());
	}
}
