package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.BaseEntity;

public class PagedQueryResponse {
	private List<? extends BaseEntity> result;
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<? extends BaseEntity> getResult() {
		return result;
	}

	public void setResult(List<? extends BaseEntity> result) {
		this.result = result;
	}

}