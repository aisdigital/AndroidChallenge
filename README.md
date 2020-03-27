# Android Challenge

AIS Android Challenge

Hey, whats up? Are you ready to start the challenge? We would like to remember that this step will help us evaluate your performance. Take a deep breath and let’s go!
We’re rooting for you. 😁

## Some Rules

1 - **DO NOT** share your answer with others.

2 - Remember that this challenge it's meant to evaluate your skills, you don't necessarily have to finish **all** the challenge code implementation, we just want to get know you better. 😁

## Steps

- Fork the repository.
- Create a branch (from dev branch) with the following pattern:
  Use your name as branch name, eg:
  Considering that my name is Guilherme Prado the branch name should be: "feature/guilherme_prado"
- Work **only** on your branch, and after you finish, create a Pull Request to "dev" branch on this repository.
- **DO NOT** change the reviewers of your Pull Request.

## API

You don't need to use all the following endpoints, but depending on your challenge, you might need to use one or more.

Base url is https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io

### List Endpoint
- If you need to do an API call to create a list on the challenge, use the following Endpoint:
  
  GET
  The path is /teams.
  
  Response example (List):
  {
    "name": "Celtics",
    "city": "New Jersey",
    "conference": "EAST",
    "teamImageUrl": "https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png",
    "description": "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na
      cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se
      mantém desde que foi criada."
  }
  
### Login Endpoint
 To do the login, you need to use these two endpoints, the first one will give you an auth-token, and you need to use this token on the second request to get the user info.

- If you need to do an API call to login, use the following Endpoint:
  
  POST
  The path is /auth
  
  Query Params:
  user_email - The user email.
  user_password - The user password.
  
  Response example:
  {
    "token" : "jkrdvjnksdv89qefn8fui31ncdn8"
  }
  
- And after this, use this one to finish the Login step:
 
  GET
  The path is /login
  
  Headers:
  x-auth-token - The token received on the "/auth" response.
  
  Response example:
  {
    "name": "Jon Doe",
    "age": "20",
    "gender": "MALE"
  }

Let The challenge begin!
