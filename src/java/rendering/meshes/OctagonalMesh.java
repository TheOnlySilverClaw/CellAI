package rendering.meshes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class OctagonalMesh implements Mesh {

	protected final int vertexArrayId, vertexBufferId;
	
	public OctagonalMesh(float size) {
		
		float [] vertices = new float [] {
			0f, 0f, //center

			0f, size + 0.2f,
			size/2, size/2,
			size + 0.2f, 0,
			size/2, -size/2,
			
			0, -size -0.2f,
			-size/2, -size/2,
			-size - 0.2f, 0,
			-size/2, size/2,
			0, size + 0.2f,
			
			0f, 0f
		};
		
		vertexArrayId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayId);
		
		vertexBufferId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void render() throws Exception {
		
		GL30.glBindVertexArray(vertexArrayId);
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, 10);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void release() throws Exception {
	
		GL15.glDeleteBuffers(vertexBufferId);
		GL30.glDeleteVertexArrays(vertexArrayId);
	}
}
