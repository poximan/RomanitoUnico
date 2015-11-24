package romaneo.unificado.daos;

import romaneo.unificado.domain.Logging;

public class LoggingDaoImple extends BaseDaoImple<Logging, Integer> implements LoggingDao {

	@Override
	protected Class<Logging> getEntityClass() {
		return Logging.class;
	}
}
