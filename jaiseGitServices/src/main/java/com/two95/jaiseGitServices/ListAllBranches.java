package com.two95.jaiseGitServices;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class ListAllBranches {
	public static void listBranches(String localGitRepoPath ){
		
		FileRepositoryBuilder builder = new FileRepositoryBuilder();

		try {
			Repository repository = builder.setGitDir(new File(localGitRepoPath)).readEnvironment().findGitDir()
					.build();

			Git git = new Git(repository);
			List<Ref> branch = git.branchList().call();
			branch = git.branchList().setListMode(ListMode.ALL).call();
			for (Ref ref : branch) {
				System.out.println("Branch "+ref.getName());
			}
	}catch(Exception E){System.out.println("repo path not valid or repo does not exist if so please clone");}
		}

}
