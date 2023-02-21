package baodientu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import baodientu.entity.Account;
import baodientu.form.CreateAccountForm;
import baodientu.form.UpdateAccountForm;
import baodientu.form.AccountFilterForm;

public interface IAccountService extends UserDetailsService{
	public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm);

	public Account getAccountById(int id);

	public Account getAccountByUsername(String username);

	public void createAccount(CreateAccountForm form);

	public void updateAccount(int id, UpdateAccountForm form);

	public void deleteAccount(int id);

	public boolean isAccountExistsByUsername(String username);

	void sendRegistrationAccountConfirm(String email);

	void activeAccount(String token);
}
