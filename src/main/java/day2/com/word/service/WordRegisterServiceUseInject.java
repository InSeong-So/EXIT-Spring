package day2.com.word.service;

import javax.inject.Inject;
import javax.inject.Named;

import day2.com.word.WordSet;
import day2.com.word.dao.WordDao;

public class WordRegisterServiceUseInject {

	@Inject
	@Named(value = "wordDao1")
	private WordDao wordDao;

	public WordRegisterServiceUseInject() {

	}

	// @Inject
	public WordRegisterServiceUseInject(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if (verify(wordKey)) {
			wordDao.insert(wordSet);
		} else {
			System.out.println("The word has already registered.");
		}
	}

	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}

	// @Inject
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}
