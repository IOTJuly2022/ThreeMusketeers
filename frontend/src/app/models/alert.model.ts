class Alert {
  id: string = 'main';
  type: AlertType = AlertType.Info;
  message: string = '';
  persist: boolean = false;
  fade: boolean = false;

  constructor(init?: Partial<any>) {
    Object.assign(this, init);
  }

  getCSSClasses(): string {
    return `alert alert-${this.type} ${this.fade ? 'fade' : ''}`
  }
}

enum AlertType {
  Success = 'success',
  Error = 'danger',
  Warning = 'warning',
  Info = 'info',
}

export {Alert, AlertType};
