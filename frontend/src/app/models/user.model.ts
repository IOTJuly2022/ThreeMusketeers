import {Permission} from "./permission.model";
import {Role} from "./role.model";

export interface User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  roles: Role[];
  permissions: Permission[];
}
