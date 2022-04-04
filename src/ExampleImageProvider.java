import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Image;

public class ExampleImageProvider {

	private static LocalResourceManager localResourceManager;

	public static Image getImage(String image) {
		if(localResourceManager == null) {
			localResourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return localResourceManager.createImage(
				ImageDescriptor.createFromFile(DynamicCssExamplePart.class, image));
	}
	
	public static void cleanResources() {
		localResourceManager = null;
	}
	
}
