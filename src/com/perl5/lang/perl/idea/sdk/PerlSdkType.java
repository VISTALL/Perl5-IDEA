package com.perl5.lang.perl.idea.sdk;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.util.ExecUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.perl5.PerlIcons;

/**
 * Created by ELI-HOME on 04-Jun-15.
 */
public class PerlSdkType extends SdkType
{
	public static final String PERL_SDK_TYPE_ID = "Perl5 Interpreter";

	public static final Pattern perlVersionStringPattern = Pattern.compile("\\(([^)]+?)\\) built for (.+)");

	public PerlSdkType()
	{
		super(PERL_SDK_TYPE_ID);
	}

	@NotNull
	public static PerlSdkType getInstance()
	{
		PerlSdkType instance = SdkType.EP_NAME.findExtension(PerlSdkType.class);
		assert instance != null : "Make sure PerlSdkType is registered in plugin.xml";
		return instance;
	}

	@Override
	public void setupSdkPaths(@NotNull Sdk sdk)
	{
		SdkModificator sdkModificator = sdk.getSdkModificator();

		for (String perlLibPath : getINCPaths(sdk.getHomePath()))
		{
			File libDir = new File(perlLibPath);

			if (libDir.exists() && libDir.isDirectory())
			{
				VirtualFile virtualDir = LocalFileSystem.getInstance().findFileByIoFile(libDir);
				if (virtualDir != null)
				{
					sdkModificator.addRoot(virtualDir, OrderRootType.SOURCES);
					sdkModificator.addRoot(virtualDir, OrderRootType.CLASSES);
				}
			}
		}

		sdkModificator.commitChanges();
	}

	public List<String> getINCPaths(String sdkHomePath)
	{
		String executablePath = getExecutablePath(sdkHomePath);
		List<String> perlLibPaths = new ArrayList<String>();
		if (executablePath != null)
		{
			for (String path : getDataFromProgram(
					executablePath,
					"-le",
					"print for @INC"
			))
				if (!".".equals(path))
					perlLibPaths.add(path);
		}
		return perlLibPaths;
	}

	@Override
	public String getPresentableName()
	{
		return "Perl5 Interpreter";
	}

	@NotNull
	@Override
	public Collection<String> suggestHomePaths()
	{
		String path = suggestHomePath();
		if(path != null)
		{
			return Collections.singletonList(path);
		}
		return super.suggestHomePaths();
	}

	@Nullable
	public String suggestHomePath()
	{
		String perlPath = getPathFromPerl();
		if (perlPath != null)
			return perlPath;

		if (SystemInfo.isLinux || SystemInfo.isUnix || SystemInfo.isFreeBSD)
			return "/usr/bin/";

		return System.getenv("PERL_HOME");
	}

	@Override
	public String suggestSdkName(String currentSdkName, String sdkHome)
	{
		return "Perl " + getPerlVersionString(sdkHome);
	}

	@Override
	public boolean isValidSdkHome(String sdkHome)
	{
		File f = new File(getExecutablePath(sdkHome));
		return f.exists();
	}

	private String getExecutablePath(Sdk sdk)
	{
		String sdkHomePath = sdk.getHomePath();
		if (sdkHomePath != null)
			return getExecutablePath(sdkHomePath);
		else
			return null;
	}


	private String getExecutablePath(String sdkHome)
	{
		if (!(sdkHome.endsWith("/") && sdkHome.endsWith("\\")))
			sdkHome += File.separator;

		if (SystemInfo.isWindows)
			return sdkHome + "perl.exe";
		else
			return sdkHome + "perl";
	}

	@Override
	public Icon getIcon()
	{
		return PerlIcons.PERL_LANGUAGE;
	}

	@Nullable
	@Override
	public String getVersionString(@NotNull String sdkHome)
	{
		return getPerlVersionString(sdkHome);
	}

	public String getPerlVersionString(@NotNull String sdkHomePath)
	{
		List<String> versionLines = getDataFromProgram(getExecutablePath(sdkHomePath), "-v");

		if (versionLines.size() > 0)
		{
			Matcher m = perlVersionStringPattern.matcher(versionLines.get(0));

			if (m.find())
				return m.group(1) + " (" + m.group(2) + ")";

			return "Unknown version";
		}

		throw new RuntimeException("Error getting perl version text");
	}


	public String getPathFromPerl()
	{
		List<String> perlPathLines = getDataFromProgram("perl", "-le", "print $^X");

		if (perlPathLines.size() == 1)
		{
			int perlIndex = perlPathLines.get(0).lastIndexOf("perl");
			if (perlIndex > 0)
				return perlPathLines.get(0).substring(0, perlIndex);

		}
		return null;
	}


	public List<String> getDataFromProgram(String... command)
	{
		try
		{
			return ExecUtil.execAndGetOutput(new GeneralCommandLine(command)).getStdoutLines(true);

		} catch (Exception e)
		{
//			throw new IncorrectOperationException("Error executing external perl, please report to plugin developers: " + e.getMessage());
			return Collections.emptyList();
		}
	}


}
