package com.two95.jaiseGitServices;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

public class SearchinBranches {

	@SuppressWarnings("resource")
	public static void searchBranches(String localGitRepoPath, String fileName) {

		Set<String> lstbr = new TreeSet<>();
		FileRepositoryBuilder builder = new FileRepositoryBuilder();

		try {
			Repository repository = builder.setGitDir(new File(localGitRepoPath)).readEnvironment().findGitDir()
					.build();

			Git git = new Git(repository);
			List<Ref> branch = git.branchList().call();
			branch = git.branchList().setListMode(ListMode.ALL).call();
			for (Ref ref : branch) {
				lstbr.add(ref.getName());

				RevWalk walk = new RevWalk(repository);

				RevCommit commit = walk.parseCommit(ref.getObjectId());
				RevTree tree = commit.getTree();

				TreeWalk treeWalk = new TreeWalk(repository);
				treeWalk.addTree(tree);
				treeWalk.setRecursive(true);
				Boolean fun = false;

				while (treeWalk.next()) {
					fun = treeWalk.getPathString().contentEquals(fileName);

					if (fun) {
						lstbr.remove(ref.getName());

					}

				}
			}
			repository.close();

		} catch (Exception ex) {
			System.out.println("ex");
		}

		for (String br : lstbr) {
			System.out.println(br);
		}
		

	}
}


