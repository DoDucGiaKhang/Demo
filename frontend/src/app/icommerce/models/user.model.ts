export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  token: string;
  avatarData: Uint8Array;
  roles: string[];
}
