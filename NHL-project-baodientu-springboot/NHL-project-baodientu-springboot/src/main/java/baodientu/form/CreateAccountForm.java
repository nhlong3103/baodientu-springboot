package baodientu.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountForm {
	private String username;

	private String email;

	private String password;
}
