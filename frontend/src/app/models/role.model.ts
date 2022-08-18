import {Permission} from "./permission.model";

/**
 * Represents a user role
 */
export interface Role {
  id: number;
  name: string;
  permissions: Permission[];
}
