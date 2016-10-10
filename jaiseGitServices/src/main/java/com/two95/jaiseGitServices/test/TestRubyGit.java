package com.two95.jaiseGitServices.test;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.eclipse.egit.github.core.RepositoryTag;
import org.eclipse.egit.github.core.Tree;
import org.eclipse.egit.github.core.TreeEntry;
import org.eclipse.egit.github.core.service.DataService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class TestRubyGit {

	private static final String TRAVIS_YML = ".travis.yml";
	private static final String USER_NAME_RUBY = "ruby";
	private static final String NO_COMMITS = "No Commits";

	public static void main(String[] args) throws IOException{
		final String user = USER_NAME_RUBY;
		final String repoformat = "{0}) {1}- created on {2}";
		final String branchformat = "{0}) Branch Name {1}";
		final String branchFormatWithFile = "{0}) Branch Name {1} does not contain {2}";
		final String tagformat = "{0}) Tag Name {1}";
		int count = 1;
		final RepositoryService repositoryService = new RepositoryService();
		final DataService dataService = new DataService();
		Set<String> tagSet = new HashSet<>();
		Set<String> branchSet = new HashSet<>();
		Repository rubyRepo = null;
		try {
			System.out.println("<---------------------------REPOSITORIES------------------------->");
			for (Repository repo : repositoryService.getRepositories(user)) {
				System.out.println(MessageFormat.format(repoformat, count++,
						repo.getName(), repo.getCreatedAt()));
				if(USER_NAME_RUBY.equals(repo.getName())) {
					rubyRepo = repo;
				}
			}
			System.out.println("<---------------------------BRANCHES------------------------->");
			List<RepositoryBranch> repoBranches = repositoryService.getBranches(rubyRepo);
			for(RepositoryBranch repoBranch : repoBranches) {
				System.out.println(MessageFormat.format(branchformat, count++,
						repoBranch.getName() ));
				Tree tree = dataService.getTree(rubyRepo, repoBranch.getCommit().getSha());
				List<TreeEntry> treeEntries = tree.getTree();
				for(TreeEntry entry : treeEntries) {
					String path = entry.getPath();
					if(path!=null && !path.contains(TRAVIS_YML)) {
						branchSet.add(MessageFormat.format(branchFormatWithFile, count++,
								repoBranch.getName(),TRAVIS_YML));
					}
					
				}
				
			}
			System.out.println("<---------------------------TAGS------------------------->");
			List<RepositoryTag> tags = repositoryService.getTags(rubyRepo);
			for(RepositoryTag tag : tags) {
				System.out.println(MessageFormat.format(tagformat, count++,
						tag.getName(), tag.getCommit()!=null?tag.getCommit().getSha():NO_COMMITS));
				
				Tree tree = dataService.getTree(rubyRepo, tag.getCommit().getSha());
				List<TreeEntry> treeEntries = tree.getTree();
				for(TreeEntry entry : treeEntries) {
					String path = entry.getPath();
					if(path!=null && !path.contains(TRAVIS_YML)) {
						tagSet.add(MessageFormat.format(branchFormatWithFile, count++,
								tag.getName(),TRAVIS_YML));
					}
				}
					
			}
			
			System.out.println("<---------------------------BRANCH WITH TRAVIS.YML------------------------->");
			System.out.println(branchSet);
			System.out.println("<---------------------------TAG WITH TRAVIS.YML------------------------->");
				System.out.println(tagSet);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
