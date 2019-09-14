// class of model JwtModel for used in all components of the application
// This model give access to the token, username (email) and privileges of the users
export class JwtModel {
  token: string;
  type: string;
  email: string;
  authorities: string[];
}
