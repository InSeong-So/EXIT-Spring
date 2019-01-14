package day3.com.brms.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import day3.com.brms.member.Member;
import day3.com.brms.member.dao.MemberDao;

public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService() {
	}

	public void register(Member member) {
		memberDao.insert(member);
	}
}
