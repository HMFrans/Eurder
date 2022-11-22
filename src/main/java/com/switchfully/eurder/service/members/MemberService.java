package com.switchfully.eurder.service.members;

import com.switchfully.eurder.domain.members.Member;
import com.switchfully.eurder.service.members.dto.NewMemberDto;
import com.switchfully.eurder.domain.members.MemberRepository;
import com.switchfully.eurder.service.members.dto.ReturnMemberDto;
import com.switchfully.eurder.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final Validator validator;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.validator = new Validator();
        this.memberMapper = new MemberMapper();
    }

    public ReturnMemberDto addNewMember(NewMemberDto newMemberDto) {
        validator.checkRequiredFieldsForNewMember(newMemberDto);
        Member newMember = memberMapper.DtoToMember(newMemberDto);
        return memberMapper.memberToDto(memberRepository.addMember(newMember));
    }

    public void makeAdmin(String userId) {
        memberRepository.makeAdmin(userId);
    }

    public List<ReturnMemberDto> getAllMembers() {
        return memberRepository.getMembers()
                .stream()
                .map(member -> memberMapper.memberToDto(member))
                .toList();

    }

    public ReturnMemberDto getMember(String memberId) {
        return memberMapper.memberToDto(memberRepository.getMember(memberId));
    }
}
