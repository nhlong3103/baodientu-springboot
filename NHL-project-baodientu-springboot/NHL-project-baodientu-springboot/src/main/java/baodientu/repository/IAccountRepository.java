package baodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import baodientu.entity.Account;
import baodientu.entity.Account.AccountStatus;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
	
	public Account findByUsername(String name);

	public Account findByEmail(String email);

	boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
	
	@Query("SELECT 	accountStatus " + "FROM 	Account " + " WHERE email = :email")
	public AccountStatus findStatusByEmail(String email);
}
