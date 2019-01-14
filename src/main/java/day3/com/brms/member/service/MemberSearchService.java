package day3.com.brms.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import day3.com.brms.member.Member;
import day3.com.brms.member.dao.MemberDao;

public class MemberSearchService {

	@Autowired
	private MemberDao memberDao;

	public MemberSearchService() {
	}

	public Member searchMember(String mId) {
		return memberDao.select(mId);
	}

}