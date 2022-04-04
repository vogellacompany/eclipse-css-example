import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

public class ExampleLabelProvider extends ColumnLabelProvider {
	
	private TreeViewer treeViewer;
	private LocalResourceManager localResourceManager;

	public ExampleLabelProvider(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
		this.treeViewer.addPostSelectionChangedListener(event -> {
			IStructuredSelection structuredSelection = event.getStructuredSelection();
			if (structuredSelection.isEmpty()) {
				return;
			}
			ExampleLabelProvider.this.treeViewer.refresh();
		});
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(), treeViewer.getControl());
	}

	
	@Override
	public Color getForeground(Object element) {
		return super.getForeground(element);
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}

	@Override
	public Font getFont(Object element) {
		ITreeSelection structuredSelection = treeViewer.getStructuredSelection();
		if (structuredSelection.isEmpty()) {
			return null;
		}
		if (element instanceof String && structuredSelection.getFirstElement().equals(element)) {
			Font systemFont = treeViewer.getControl().getFont();
			FontData[] fontData = systemFont.getFontData();
			fontData[0].setStyle(SWT.BOLD);
			FontDescriptor createFrom = FontDescriptor.createFrom(fontData);
			return localResourceManager.createFont(createFrom);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof String) {
			return ((String) element);
		}
		return super.getText(element);
	}

	@Override
	public String getToolTipText(Object element) {
		if (element instanceof String) {
			return ((String) element);
		}
		return super.getToolTipText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof String) {
			if(((String) element).equals("with image")) {
				return ExampleImageProvider.getImage("images/favourite.png");			
			}
		}
		return super.getImage(element);
	}
}
