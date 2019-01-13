package day2.com.word.service;

import javax.annotation.Resource;

import day2.com.word.WordSet;
import day2.com.word.dao.WordDao;

public class WordRegisterServiceUseResource {

	@Resource
	// @Qualifier("usedDao")
	private WordDao wordDao;

	public WordRegisterServiceUseResource() {

	}

	// @Resource
	public WordRegisterServiceUseResource(WordDao wordDao) {
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

	// @Resource
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}