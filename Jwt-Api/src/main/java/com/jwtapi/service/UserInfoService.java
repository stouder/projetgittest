package com.jwtapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtapi.adapter.UserInfosAdapter;
import com.jwtapi.common.NoResultFoundException;
import com.jwtapi.dto.UserInfosDTO;
import com.jwtapi.model.UserInfo;
import com.jwtapi.model.UserInfoDetails;
import com.jwtapi.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserInfosAdapter userInfosAdapter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userDetail = repository.findUserByName(username);

		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "User Added Successfully";
	}

	public String findByName(String name) {
		Optional<UserInfo> userInfoOptional = repository.findByName(name);
		return userInfoOptional
				.map(userInfo -> "Profile User: Nom - " + userInfo.getName() + ", Email - " + userInfo.getEmail())
				.orElse("User not found");

	}

	public UserInfosDTO findEntityByName(String name) {
		return repository.findByName(name).map(userInfosAdapter::transform).orElseThrow(
				() -> new NoResultFoundException("Aucun utilisateur courant trouvé avec le nom : " + name));

	}

	public UserInfosDTO findById(UUID id) {
		return repository.findById(id).map(userInfosAdapter::transform)
				.orElseThrow(() -> new NoResultFoundException("Aucun utilisateur pour l'identifiant  : " + id));

	}

	public List<UserInfosDTO> findAll() {
		return repository.findAll().stream().map(userInfosAdapter::transform).toList();
	}
}