package baodientu.specification.danhmuc;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import baodientu.entity.DanhMuc;
import baodientu.form.DanhMucFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class DanhMucSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<DanhMuc> buildWhere(String search, DanhMucFilterForm filterForm) {

		Specification<DanhMuc> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification name = new CustomSpecification("name", search);
			CustomSpecification username = new CustomSpecification("username", search);
			where = Specification.where(name).or(username);
		}
		// if there is filter by min created date
		if (filterForm != null && filterForm.getCreatedDate() != null) {
			CustomSpecification createdDate = new CustomSpecification("createdDate", filterForm.getCreatedDate());
			if (where == null) {
				where = createdDate;
			} else {
				where = where.and(createdDate);
			}
		}

		// if there is filter by min created date
		if (filterForm != null && filterForm.getMinCreatedDate() != null) {
			CustomSpecification minCreatedDate = new CustomSpecification("minCreatedDate",
					filterForm.getMinCreatedDate());
			if (where == null) {
				where = minCreatedDate;
			} else {
				where = where.and(minCreatedDate);
			}
		}

		// if there is filter by max created date
		if (filterForm != null && filterForm.getMaxCreatedDate() != null) {
			CustomSpecification maxCreatedDate = new CustomSpecification("maxCreatedDate",
					filterForm.getMaxCreatedDate());
			if (where == null) {
				where = maxCreatedDate;
			} else {
				where = where.and(maxCreatedDate);
			}
		}

		// if there is filter by min year
		if (filterForm != null && filterForm.getMinYear() != null) {
			CustomSpecification minYear = new CustomSpecification("minYear", filterForm.getMinYear());
			if (where == null) {
				where = minYear;
			} else {
				where = where.and(minYear);
			}
		}
		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<DanhMuc> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<DanhMuc> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("username")) {
			Join join = root.join("accounts", JoinType.LEFT);
			return criteriaBuilder.like(join.get("username"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("createdDate")) {
			return criteriaBuilder.equal(root.get("createdDate").as(java.sql.Date.class), (Date) value);
		}

		if (field.equalsIgnoreCase("minCreatedDate")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(java.sql.Date.class), (Date) value);
		}

		if (field.equalsIgnoreCase("maxCreatedDate")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(java.sql.Date.class), (Date) value);
		}

		if (field.equalsIgnoreCase("minYear")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					criteriaBuilder.function("YEAR", Integer.class, root.get("createdDate")), (Integer) value);
		}

		if (field.equalsIgnoreCase("type")) {
			return criteriaBuilder.equal(root.get("type"), value);
		}

		return null;
	}
}
