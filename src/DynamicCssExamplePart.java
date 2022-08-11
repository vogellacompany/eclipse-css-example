 

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PlainMessageDialog;
import org.eclipse.jface.dialogs.PlainMessageDialog.Builder;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;

@SuppressWarnings("restriction")
public class DynamicCssExamplePart {
	
	private static final String IMAGES_HELP_PNG = "images/help.png";
	private static final String IMAGES_INFO_PNG = "images/info.png";
	private static final String IMAGES_FAVOURITE_PNG = "images/favourite.png";
	
	
	@Inject
	public DynamicCssExamplePart() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, IThemeEngine themeEngine) {
		parent.setLayout(GridLayoutFactory.swtDefaults().margins(24,24).spacing(24,24).numColumns(5).create());

		RowLayoutFactory verticalRowLayout = RowLayoutFactory.swtDefaults().type(SWT.VERTICAL);
		GridDataFactory layoutDataFactory = GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP);
		
		// RADIO
		Group radioButtonGroup = new Group(parent, SWT.NONE);
		radioButtonGroup.setText("Button RADIO");
		radioButtonGroup.setLayout(verticalRowLayout.create());
		radioButtonGroup.setLayoutData(layoutDataFactory.create());
		
		Button radio1 = new Button(radioButtonGroup, SWT.RADIO);
		radio1.setText("one");
		radio1.setImage(ExampleImageProvider.getImage(IMAGES_INFO_PNG));
		radio1.setSelection(true);
		
		Button radio2 = new Button(radioButtonGroup, SWT.RADIO);
		radio2.setText("two");
		radio2.setImage(ExampleImageProvider.getImage(IMAGES_HELP_PNG));
		
		Button radio3 = new Button(radioButtonGroup, SWT.RADIO);
		radio3.setImage(ExampleImageProvider.getImage(IMAGES_FAVOURITE_PNG));
		
		Button radio4 = new Button(radioButtonGroup, SWT.RADIO);
		radio4.setText("four");
		radio4.setEnabled(false);
		
		
		// TOGGLE
		Group toggleButtonGroup = new Group(parent, SWT.NONE);
		toggleButtonGroup.setLayout(verticalRowLayout.create());
		toggleButtonGroup.setText("Button TOGGLE");
		toggleButtonGroup.setLayoutData(layoutDataFactory.create());

		Button toggle1 = new Button(toggleButtonGroup, SWT.TOGGLE);
		toggle1.setText("one");
		toggle1.setImage(ExampleImageProvider.getImage(IMAGES_INFO_PNG));
		toggle1.setSelection(true);
		
		Button toggle2 = new Button(toggleButtonGroup, SWT.TOGGLE);
		toggle2.setText("two");
		Button toggle3 = new Button(toggleButtonGroup, SWT.TOGGLE);
		toggle3.setText("enable/disable tree");
		toggle3.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		toggle3.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		Button toggle4 = new Button(toggleButtonGroup, SWT.TOGGLE);
		toggle4.setText("four");
		toggle4.setEnabled(false);
		
		
		
		// CHECK
		Group checkButtonGroup = new Group(parent, SWT.NONE);
		checkButtonGroup.setLayout(verticalRowLayout.create());
		checkButtonGroup.setText("Button CHECK");
		checkButtonGroup.setLayoutData(layoutDataFactory.create());

		Button check1 = new Button(checkButtonGroup, SWT.CHECK);
		check1.setText("one");
		
		Button check2 = new Button(checkButtonGroup, SWT.CHECK);
		check2.setText("two");
		check2.setImage(ExampleImageProvider.getImage(IMAGES_FAVOURITE_PNG));
		check2.setSelection(true);
		
		Button check3 = new Button(checkButtonGroup, SWT.CHECK);
		check3.setEnabled(false);
		check3.setImage(ExampleImageProvider.getImage(IMAGES_HELP_PNG));
		
		Button check4 = new Button(checkButtonGroup, SWT.CHECK);
		check4.setText("four");
		check4.setEnabled(false);
		
		Button check5 = new Button(checkButtonGroup, SWT.CHECK);
		check5.setText("five");
		
		
		// PUSH
		Group pushButtonGroup = new Group(parent, SWT.NONE);
		pushButtonGroup.setLayout(verticalRowLayout.create());
		pushButtonGroup.setText("Button PUSH");
		pushButtonGroup.setLayoutData(layoutDataFactory.create());
		
		
		Button push1 = new Button(pushButtonGroup, SWT.PUSH);
		push1.setText("enable/disable toolbar");
		
		Button push2 = new Button(pushButtonGroup, SWT.PUSH);
		push2.setText("disable self");
		push2.setImage(ExampleImageProvider.getImage(IMAGES_HELP_PNG));
		push2.addListener(SWT.Selection, event -> {
			push2.setEnabled(false);
		});
		
		Button push3 = new Button(pushButtonGroup, SWT.PUSH);
		push3.setText("three");
		push3.setEnabled(false);
		
		
		Button push4 = new Button(pushButtonGroup, SWT.PUSH);
		push4.setText("disable all");
		push4.addListener(SWT.Selection, event -> {
			boolean enabled = false;
			radio1.setEnabled(enabled);
			radio2.setEnabled(enabled);
			radio3.setEnabled(enabled);
			radio4.setEnabled(enabled);

			toggle1.setEnabled(enabled);
			toggle2.setEnabled(enabled);
			toggle3.setEnabled(enabled);
			toggle4.setEnabled(enabled);
			
			check1.setEnabled(enabled);
			check2.setEnabled(enabled);
			check3.setEnabled(enabled);
			check4.setEnabled(enabled);
			check5.setEnabled(enabled);

			push1.setEnabled(enabled);
			push2.setEnabled(enabled);
			push3.setEnabled(enabled);
			
			// Because listeners are not called by SWT when setEnabled / setSelection is called by code, 
			// we need to trigger the styling here. Quite ugly:
			themeEngine.applyStyles(parent, true);
		});
		
		Button push5 = new Button(pushButtonGroup, SWT.PUSH);
		push5.setText("enable all");
		push5.addListener(SWT.Selection, event -> {
			boolean enabled = true;
			radio1.setEnabled(enabled);
			radio2.setEnabled(enabled);
			radio3.setEnabled(enabled);
			radio4.setEnabled(enabled);

			toggle1.setEnabled(enabled);
			toggle2.setEnabled(enabled);
			toggle3.setEnabled(enabled);
			toggle4.setEnabled(enabled);
			
			check1.setEnabled(enabled);
			check2.setEnabled(enabled);
			check3.setEnabled(enabled);
			check4.setEnabled(enabled);
			check5.setEnabled(enabled);

			push1.setEnabled(enabled);
			push2.setEnabled(enabled);
			push3.setEnabled(enabled);
			
			// Because listeners are not called by SWT when setEnabled / setSelection is called by code, 
			// we need to trigger the styling here. Quite ugly:
			themeEngine.applyStyles(parent, true);
		});
		
		
		// example for default button behavior
		Group defaultButtonGroup = new Group(parent, SWT.NONE);
		defaultButtonGroup.setLayout(verticalRowLayout.create());
		defaultButtonGroup.setText("Default Button Group");
		defaultButtonGroup.setLayoutData(layoutDataFactory.create());
		
		Button stdButton = new Button(defaultButtonGroup, SWT.PUSH);
		stdButton.setText("enable/disable default button");
		
		
		Button defaultButton = new Button(defaultButtonGroup, SWT.PUSH);
		defaultButton.setText("Eat all cookies");
		defaultButton.setImage(ExampleImageProvider.getImage(IMAGES_HELP_PNG));
		defaultButtonGroup.getShell().setDefaultButton(defaultButton);
		stdButton.addListener(SWT.Selection, event -> {
			defaultButton.setEnabled(!defaultButton.getEnabled());
			
			// Because listeners are not called by SWT when setEnabled / setSelection is called by code, 
			// we need to trigger the styling here.
			themeEngine.applyStyles(defaultButton, false);
		});
		defaultButton.setData("org.eclipse.e4.ui.css.CssClassName", "DefaultButton");

		// Trying to style default buttons in jface dialog:
		ArrayList<String> buttonStrings = new ArrayList<>(Arrays.asList("OK", "Not today"));
		defaultButton.addListener(SWT.Selection, event -> {
			PlainMessageDialog dialog = PlainMessageDialog.getBuilder(defaultButton.getShell(), "Popup").message("Let's style default buttons!").buttonLabels(buttonStrings).defaultButtonIndex(1).build();
						
			// There is no method to retrieve and/or style the default button of the MessageDialog or PlainMessageDialog.
			// Other third-party components may have inaccessible default buttons as well. 
			// Not possible:
			// dialog.getDefaultButton().setData("org.eclipse.e4.ui.css.CssClassName", "DefaultButton");

			dialog.open();
		});
		
		
		// Group for toolbar			
		Group toolbarGroup = new Group(parent, SWT.NONE);
		toolbarGroup.setLayout(verticalRowLayout.create());
		toolbarGroup.setLayoutData(GridDataFactory.swtDefaults().span(4, 1).align(SWT.LEFT, SWT.TOP).create());
		toolbarGroup.setText("ToolBar");
		
		ToolBar toolBar = new ToolBar(toolbarGroup, SWT.FLAT | SWT.RIGHT);

		ToolItem toolItemPush = new ToolItem(toolBar, SWT.PUSH);
		toolItemPush.setText("Text Button");
		toolItemPush.setToolTipText("Text Button Tooltip");
		toolItemPush.addListener(SWT.Selection, e -> System.out.println("push"));

		// trying new ToolItem API (Eclipse 4.24)
//		toolItemPush.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
//		toolItemPush.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		
		ToolItem toolItemCheck = new ToolItem(toolBar, SWT.CHECK);
		toolItemCheck.setText("Check Button");
		toolItemCheck.setToolTipText("Check Button Tooltip");
		toolItemCheck.addListener(SWT.Selection, e -> System.out.println("check"));
//		toolItemCheck.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
//		toolItemCheck.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		
		ToolItem toolItemRadio = new ToolItem(toolBar, SWT.CHECK);
		toolItemRadio.setText("Radio Button");
		toolItemRadio.setToolTipText("Radio Button Tooltip");
		toolItemRadio.addListener(SWT.Selection, e -> System.out.println("radio"));
		
		ToolItem toolItemIconPush = new ToolItem(toolBar, SWT.PUSH);
		toolItemIconPush.setImage(ExampleImageProvider.getImage(IMAGES_INFO_PNG));
		toolItemIconPush.addListener(SWT.Selection, e -> System.out.println("blip icon push"));
//		toolItemIconPush.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
//		toolItemIconPush.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));

		ToolItem toolItemIconCheck= new ToolItem(toolBar, SWT.CHECK);
		toolItemIconCheck.setImage(ExampleImageProvider.getImage(IMAGES_FAVOURITE_PNG));
		toolItemIconCheck.addListener(SWT.Selection, e -> toolItemPush.setEnabled(!toolItemPush.getEnabled()));
//		toolItemIconCheck.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
//		toolItemIconCheck.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));

		ToolItem toolItemIconRadio = new ToolItem(toolBar, SWT.RADIO);
		toolItemIconRadio.setImage(ExampleImageProvider.getImage(IMAGES_HELP_PNG));
		toolItemIconRadio.addListener(SWT.Selection, e -> System.out.println("blip icon radio"));
				
		push1.addListener(SWT.Selection, e -> toolBar.setEnabled(!toolBar.getEnabled()));

		
		// Group for Tree
		Group treeGroup = new Group(parent, SWT.NONE);
		treeGroup.setLayout(verticalRowLayout.create());
		treeGroup.setLayoutData(layoutDataFactory.grab(true, true).create());
		treeGroup.setText("Tree");
		
		TreeViewer treeViewer = new TreeViewer(treeGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setLabelProvider(new ExampleLabelProvider(treeViewer));
		treeViewer.setContentProvider(new ExampleContentProvider());
		treeViewer.setInput("ASDF");
		treeViewer.addTreeListener(new ITreeViewerListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				treeGroup.requestLayout();
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				treeGroup.requestLayout();
			}
		});
		
		treeViewer.getTree().setData("org.eclipse.e4.ui.css.CssClassName", "MyTree");
		toggle3.addListener(SWT.Selection, event -> {
			treeViewer.getControl().setEnabled(toggle3.getSelection());
		});

		
	}


	
		
}