# Services

Services is where all of the business logic for our application lives. Note that we should
not be doing any sort of data access or anything like that, just pure business logic. The
main motivation is that it allows us to reuse the same business logic no matter the presentation
layer (e.g. programatic API vs UI). By supporting multiple presentation layers, we also benefit
from having this logic in one place, because we all know that even simplel ogic grows over time!

Building a service is pretty simple, but should strive to meet the following goals:

- All side-effecting actions are done through repositories (repos)
- All repositories are provided at service instantiation
- All repository definitions use the repository interface
- All methods should be as "pure" as possible (excluding side-effects done through repos)
- All services should have a Trait to define it

This ensures that we get fairly testable and functional code as possible.
