/*
 * Copyright 2015 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl.idea.configuration.paths;

import com.intellij.facet.impl.DefaultFacetsProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.impl.ModuleConfigurationStateImpl;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.CommonContentEntriesEditor;
import com.intellij.openapi.roots.ui.configuration.DefaultModulesProvider;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.perl5.lang.perl.idea.modules.JpsPerlLibrarySourceRootType;
import org.jetbrains.jps.model.module.JpsModuleSourceRootType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hurricup on 29.08.2015.
 */
public class PerlPlatformContentEntriesConfigurable implements Configurable
{
	private final Module myModule;
	private final JpsModuleSourceRootType<?>[] myRootTypes;
	private final JPanel myTopPanel = new JPanel(new BorderLayout());
	private ModifiableRootModel myModifiableModel;
	private CommonContentEntriesEditor myEditor;


	public PerlPlatformContentEntriesConfigurable(Project project)
	{
		this(ModuleManager.getInstance(project).getModules()[0], JpsPerlLibrarySourceRootType.INSTANCE);
	}

	private PerlPlatformContentEntriesConfigurable(final Module module, JpsModuleSourceRootType<?>... rootTypes)
	{
		myModule = module;
		myRootTypes = rootTypes;
	}


	@Override
	public String getDisplayName()
	{
		return "Project Structure";
	}

	@Override
	public String getHelpTopic()
	{
		return null;
	}

	@Override
	public JComponent createComponent()
	{
		createEditor();
		return myTopPanel;
	}

	private void createEditor()
	{
		myModifiableModel = ApplicationManager.getApplication().runReadAction(new Computable<ModifiableRootModel>()
		{
			@Override
			public ModifiableRootModel compute()
			{
				return ModuleRootManager.getInstance(myModule).getModifiableModel();
			}
		});

		final ModuleConfigurationStateImpl moduleConfigurationState =
				new ModuleConfigurationStateImpl(myModule.getProject(), new DefaultModulesProvider(myModule.getProject()))
				{
					@Override
					public ModifiableRootModel getRootModel()
					{
						return myModifiableModel;
					}

					@Override
					public FacetsProvider getFacetsProvider()
					{
						return DefaultFacetsProvider.INSTANCE;
					}
				};
		myEditor = new PerlContentEntriesEditor(myModule.getName(), moduleConfigurationState, myRootTypes)
		{
			@Override
			protected java.util.List<ContentEntry> addContentEntries(VirtualFile[] files)
			{
				java.util.List<ContentEntry> entries = super.addContentEntries(files);
				addContentEntryPanels(entries.toArray(new ContentEntry[entries.size()]));
				return entries;
			}
		};
		JComponent component = ApplicationManager.getApplication().runReadAction(new Computable<JComponent>()
		{
			@Override
			public JComponent compute()
			{
				return myEditor.createComponent();
			}
		});
		myTopPanel.add(component, BorderLayout.CENTER);
	}

	@Override
	public boolean isModified()
	{
		return myEditor != null && myEditor.isModified();
	}

	@Override
	public void apply() throws ConfigurationException
	{
		myEditor.apply();
		if (myModifiableModel.isChanged())
		{
			ApplicationManager.getApplication().runWriteAction(new Runnable()
			{
				@Override
				public void run()
				{
					myModifiableModel.commit();
				}
			});
			myEditor.disposeUIResources();
			myTopPanel.remove(myEditor.getComponent());
			createEditor();
		}
	}

	@Override
	public void reset()
	{
		myEditor.reset();
		// TODO?
	}

	@Override
	public void disposeUIResources()
	{
		if (myEditor != null)
		{
			myEditor.disposeUIResources();
			myTopPanel.remove(myEditor.getComponent());
			myEditor = null;
		}
		if (myModifiableModel != null)
		{
			myModifiableModel.dispose();
			myModifiableModel = null;
		}
	}
}
