class Alert {
  type: AlertType = AlertType.Info;
  message: string = '';
  id: string = '';
  persist: boolean = false;
  fade: boolean = false;

  constructor(init?: Partial<any>) {
    Object.assign(this, init);
  }

  getCSSClasses(): string {
    return `alert alert-${this.type}`
  }
}

enum AlertType {
  Success = 'success',
  Error = 'error',
  Warning = 'warning',
  Info = 'info',
}

export {Alert};
