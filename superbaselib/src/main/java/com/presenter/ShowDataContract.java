package com.presenter;

public interface ShowDataContract<T> extends BaseView<DataBasePresenter> {
	void showData(T data);
	void showError(String message);
}
