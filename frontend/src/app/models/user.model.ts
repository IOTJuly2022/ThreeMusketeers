import {Permission} from "./permission.model";
import {Role} from "./role.model";

/**
 * Represents a user returned when authenticating to the backend service
 */
export interface User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  roles: Role[];
  permissions: Permission[];

  // The authorization token used in making API requests
  token: string;
}
