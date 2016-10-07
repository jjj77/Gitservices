package com.two95.jaiseGitServices.test;

import com.two95.jaiseGitServices.ListAllBranches;
import com.two95.jaiseGitServices.ListAllTags;
import com.two95.jaiseGitServices.SearchinBranches;
import com.two95.jaiseGitServices.SearchinTags;
/**
*repopath is the local git repository path
*
*
*/
public class TestJaiseGitServices {
	public static void main(String args[]){
		String repopath = "C:/Users/jjais/git/smp/.git";
		//SearchinBranches.searchBranches(repopath, ".travis.yml");
		//SearchinTags.searchBranches(repopath, ".travis.yml");
		//ListAllBranches.listBranches(repopath);
		ListAllTags.listTags(repopath);
		
	}

}
