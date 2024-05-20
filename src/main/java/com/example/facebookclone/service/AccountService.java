package com.example.facebookclone.service;

import com.example.facebookclone.DTO.SearchUserDTO;
import com.example.facebookclone.DTO.UserProfileDTO;
import com.example.facebookclone.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Account findByAccountId(int accountId);
    Account findByUsername(String username);
    Page<Account> findByProfileName(String name, int limit);
    UserProfileDTO convertToUserProfileDTO(Account account);
}
