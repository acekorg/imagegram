# Imagegram

Design and implementation by Aleksandar

## Tech stack

- Java
- Springboot
- Maven
- OpenApi

#### Deployment

Heroku:
There is `system.properties` and `Procfile` for deployment on Heroku.\
Currently available at: https://aleksandarimageg.herokuapp.com/api/imagegram/posts
Users | Passwords:
user1 | password
user2 | password
user3 | password

There is also `Dockerfile` for image deployment.

## Architecture and module organization

There are 5 Maven modules in the application: \
- **imagegramApi** - *Contains only the openApi yaml file. This makes it very light for fast download and unpack in build time when other applications integrate it.*
- **imagegramRest** - *The REST layer of the application containing generated REST models and endpoints and their implementations.*
- **imagegramCore** - *The business layer of the application containing necessary services.*
- **imagegramPersistence** - *The persistence layer currently containing only an in-memory storage*
- **imagegramApplication** - *The application packaging module*


### Module dependency graph:
```
imagegramApi <- imagegramRest -> imagegramCore -> imagegramPersistence
```
**imagegramApplication** - Has dependency to all other modules.


## Techical decisions

* Images are being served on a separate endpoint and getPosts returns only links to them. Otherwise the response would be to large and slow if we return the images together with posts.
* Because of efficiency and faster download times the API works with images in binary format opposed to Base64 which would increase the traffic load.
* Data is persisted in quick implementation of in-memory storage and deleted with restart of the application. I didn't went with H2 In-memory database because it is unsupported in Heroku for deployment.
* IDs are of UUID type just because of easier generating without implementing sequences. I have no preference practically whether I would use ID of type long or something else
* There are some small inheritance possibilities in the OpenAPI but I considered them insignificant and intentionally skipped them in order to have better and more logical naming of the models.
* I saw no reason to store the original photo for now. It is a small effort if there is need to persist it.

## Production changes
InMemoryUsers class should be either marked with `@Profile('local`) or totally removed when there is proper /register functionality is implemented.
I would remove it completely and rely on registering users from scratch in all environments.

Other than this the application currently has no environment related parameters.

#### Future expectations:
The application should always execute as in production and I wouldn't introduce any profiles unless there is strong reason for that.
Instead all of the parameters that differ by environment I would source them from Kubernetes configuration as environment variables and load them with `${placeholders}` in the yaml or with `@Value()` in the code.


## Open tasks
Some future tasks that are waiting to be implemented:

- #### Task #1: Connect to DB
The application is currently not connected to DB so the data is stored in-memory. H2 is not supported by Heroku so PostgreDB would be my choice.

- #### Task #2: Implement cursor-based pagination
This requirement is requested but not implemented.

- #### Task #3: Write K8S deployment config (In Progress)
The application deployment should be written with K8S tools (Kustomization, Helm, Terraform, Cuelang) and switch to image based deployment.

- #### Task #4: Speed-up response time by cacheing and/or indexing
Currently there are no performance issues but there are still improvement possibilities.
- *Cacheing* - There are some possibilities on the horizon like cacheing comments or cacheing images. Cacheing images would depend on the server memory as images consume more space.
- *Indexing* - As table Comments would be accessed by postId which is non-primary key neither index, it makes sense to introduce indexing by postId on this table so that binary search can be done and records will be retrieved faster.


