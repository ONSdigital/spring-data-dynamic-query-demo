# spring-data-dynamic-query-demo
How to build and execute a dynamic DB query using Spring Data (JPA, Hibernate, JPQL, HQL -> SQL)

This is a demonstration project for anybody who wishes to use something like dynamic filtering of a resultset (i.e. SQL where clause) where the database columns which are filtered are not known in advance. The specific use for Census is as a case classifier, using treatment codes, but there will be future requirements we're not able to anticipate, so we can store our classifiers as JSON and build a query dynamically, without having the risk of injection attacks, associated with allowing SQL or other query language to be stored and used directly - all parameters are bound and parsed to prevent any injection attacks.
