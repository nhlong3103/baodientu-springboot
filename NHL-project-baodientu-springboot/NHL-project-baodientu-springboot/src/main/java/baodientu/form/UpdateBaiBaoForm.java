package baodientu.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBaiBaoForm {
	
	private int id;

	private String tieuDe;

	private String gioiThieuNgan;

	private String anhTieuDe;

	private String noiDung;

	private int danhMucId;
}
