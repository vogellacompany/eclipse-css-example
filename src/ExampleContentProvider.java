import org.eclipse.jface.viewers.ITreeContentProvider;

public class ExampleContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		String[] objects = {"one", "two", "three", "with image"};
		return objects;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		String[] objects = {"one", "two", "three", "with image"};
		return objects;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return true;
	}

}
