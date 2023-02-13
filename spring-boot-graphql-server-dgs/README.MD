# Spring-boot-graphql with netflix dgs server

This service acts as backend server which provides data using graphql

There are various providers of graphql
1. graphql java kickstart
2. springboot graphql ( released 2022)
3. Third part providers (Netflix GDS, Apollo etc.)


For implementation using springboot graphql please refer to <a href="https://github.com/charanguggilam123/springboot-graphql-tutorial">Impl using springboot graphql starter</a>

Concepts Covered
1. Query
2. Mutation
3. Subscription (Altair plugin required as it needs web sockets)
4. Exception Handling
5. Data loaders (resolve 1+n )
6. Filters
7. Security is same across all impl(using spring security. Refer to springboot impl for same)

References:

<a href="https://netflix.github.io/dgs/getting-started/" target="_blank">Netflix GDS</a>
<a href="https://netflix.github.io/dgs/data-loaders/" target="_blank">Data loaders</a>
<a href="https://netflix.github.io/dgs/generating-code-from-schema/" target="_blank">Code gen plugin</a>
<a href="https://chrome.google.com/webstore/detail/altair-graphql-client/flnheeellpciglgpaodhkhmapeljopja?hl=en"target="_blank">Altair plugin</a>

