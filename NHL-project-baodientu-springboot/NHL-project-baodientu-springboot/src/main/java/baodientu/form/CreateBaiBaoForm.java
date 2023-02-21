package baodientu.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBaiBaoForm {

	private String tieuDe;

	private String gioiThieuNgan;

	private String anhTieuDe;

	private String noiDung;

	private int danhMucId;
}
