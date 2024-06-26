package com.example.facebookclone.service;

import com.cloudinary.utils.ObjectUtils;
import com.example.facebookclone.DTO.UserProfileDTO;
import com.example.facebookclone.entity.Account;
import com.example.facebookclone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByAccountId(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.get();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Page<Account> findByProfileName(String name, int limit) {
        return accountRepository.findByProfile_nameContaining(name, PageRequest.of(0, limit));
    }

    @Override
    public UserProfileDTO convertToUserProfileDTO(Account account) {
        int totalFriend = (int) (account.getFriends().stream().filter(friend -> friend.getAccept_time() != null).count() +
                        account.getFriendOf().stream().filter(friend -> friend.getAccept_time() != null).count());

        return new UserProfileDTO(account.getId(), account.getProfile_name(), account.getCome_from(), account.getLive_at(), account.getAvatar(),
                account.getCoverImage(), account.getDescription(), account.getBrithdate(), account.getCreate_time(),
                totalFriend);
    }

    @Override
    public void updateDetailInfo(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAvatar(MultipartFile avatar, int id) {
        if (avatar != null) {
            try {
                String url = cloudinary.getInstance().uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap()).values().toArray()[3].toString();
                Account account = accountRepository.findById(id).get();
                account.setAvatar(url);
                accountRepository.save(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void updateCoverImage(MultipartFile coverImage, int id) {
        if (coverImage != null) {
            try {
                String url = cloudinary.getInstance().uploader().upload(coverImage.getBytes(), ObjectUtils.emptyMap()).values().toArray()[3].toString();
                Account account = accountRepository.findById(id).get();
                account.setCoverImage(url);
                accountRepository.save(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
