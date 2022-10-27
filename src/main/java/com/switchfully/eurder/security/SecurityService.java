package com.switchfully.eurder.security;

import com.switchfully.eurder.security.exceptions.AccessDeniedException;
import com.switchfully.eurder.users.domain.Member;
import com.switchfully.eurder.users.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Base64;

@Service
public class SecurityService {

        private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

        private MemberRepository memberRepository;

        public SecurityService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        public void validateAuthorization(String authorization, Feature feature) {
            UsernamePassword usernamePassword = getUsernamePassword(authorization);
            Member member = memberRepository.getMember(usernamePassword.getUsername());
            if (member == null) {
                logger.error("Unknown user" + usernamePassword.getUsername());
                throw new RuntimeException("Wrong credentials");
            }
            if (!member.doesPasswordMatch(usernamePassword.getPassword())) {
                logger.error("Password does not match for user " + usernamePassword.getUsername());
                throw new RuntimeException("Wrong credentials");
            }
            if (!member.canHaveAccessTo(feature)) {
                logger.error("User " + usernamePassword.getUsername() + " does not have access to " + feature);
                throw new AccessDeniedException();
            }

        }

        private UsernamePassword getUsernamePassword(String authorization) {
            String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
            String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
            String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
            return new UsernamePassword(username, password);
        }
}
