import { programa } from "../programas/programa";
import { usuario } from "../usuarios/usuario";

export class matricula {
  id: number;
  usuario: usuario;
  programa: programa;
  fechaMatricula: String;
  valor: number;
  estado: string;
}
