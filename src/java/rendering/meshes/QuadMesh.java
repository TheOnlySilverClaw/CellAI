package rendering.meshes;

public class QuadMesh extends IndexMesh {

	protected QuadMesh(float[] vertices, int[] indices) {
		super(vertices, indices);
	}

	public static QuadMesh screenCentered(float scale) {
		return screenCentered(scale, scale);
	}

	public static QuadMesh screenCentered(float width, float height) {
		
		return new QuadMesh(
		new float [] {
				-width, height,
				-width, -height,
				width, -height,
				width, height
		},
		new int [] {
				0, 1, 3, 3, 1, 2
		});
	}
}
